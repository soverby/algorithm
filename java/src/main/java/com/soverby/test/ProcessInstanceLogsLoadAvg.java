package com.soverby.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.lang.String.format;

public class ProcessInstanceLogsLoadAvg {

    public static ThreadLocal<DateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() ->
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public Comparator<InstanceNodeLoadAvg> instanceNodeLoadAvgComparator =
            Comparator.comparingDouble(InstanceNodeLoadAvg::getMax);

    public static void main(String[] args) {
        ProcessInstanceLogsLoadAvg pil = new ProcessInstanceLogsLoadAvg();
        pil.process();
    }

    public static Function<String, InstanceNodeLoadAvg> parseStringToInstanceNodeLoadAvg = line -> {
        InstanceNodeLoadAvg nodeLoadAvg = new InstanceNodeLoadAvg();

        String[] commaParts = line.split(",");
        String[] nodeParts = commaParts[0].split("/");

        // Instance Node Address
        String iNodeAddr = nodeParts[2];
        nodeLoadAvg.setNodeId(iNodeAddr);

        // Date/Time
        String iNodeTimeParts = nodeParts[5];
        String[] dateTimeParts = iNodeTimeParts.split(":");
        String[] dateParts = dateTimeParts[0].split("-");
        String year = dateParts[2];
        String month = dateParts[3];
        String day = dateParts[4];

        String hour = dateTimeParts[1].trim();
        String min = dateTimeParts[2];
        String sec = dateTimeParts[3].substring(0, 2);

        String dateTimeString = format("%s-%s-%s %s:%s:%s", year, month, day, hour, min, sec);

        try {
            nodeLoadAvg.setDate(dateFormatThreadLocal.get().parse(dateTimeString));
        } catch(ParseException pe) {
            pe.printStackTrace();
        }

        int users = Integer.parseInt(commaParts[1].trim().split(" ")[0]);
        nodeLoadAvg.setUsers(users);

        nodeLoadAvg.setAvg1(Double.parseDouble(commaParts[2].trim().split(":")[1]));
        nodeLoadAvg.setAvg2(Double.parseDouble(commaParts[3].trim()));
        nodeLoadAvg.setAvg3(Double.parseDouble(commaParts[4].trim()));

        nodeLoadAvg.setMax(Math.max(Math.max(nodeLoadAvg.avg1, nodeLoadAvg.avg2), nodeLoadAvg.avg3));

        return nodeLoadAvg;
    };

    public static Function<List<InstanceNodeLoadAvg>,
            Function<Function<String, InstanceNodeLoadAvg>,
                    Consumer<String>>> instanceNodeConsumerFunction =
                        storeList -> parseFunction ->
                                (Consumer<String>) line -> {
                                    storeList.add(parseFunction.apply(line));
                                };

    public void process() {
        String fileName = "/users/sean.overby/emr/load_avg.csv";
        List<InstanceNodeLoadAvg> loadAvgList = new ArrayList<>();

        Consumer<String> loadAvgConsumer =
                instanceNodeConsumerFunction.apply(loadAvgList).apply(parseStringToInstanceNodeLoadAvg);

        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            lines.forEach(loadAvgConsumer);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        loadAvgList.sort(instanceNodeLoadAvgComparator);

        loadAvgList.forEach(nla -> System.out.println(nla.toString()));
    }

    public static class InstanceNodeLoadAvg {
        private String nodeId;
        private Date date;
        private long uptime;
        private int users;
        private double avg1;
        private double avg2;
        private double avg3;
        private double max;

        @Override
        public String toString() {
            return "InstanceNodeLoadAvg{" +
                    "nodeId='" + nodeId + '\'' +
                    ", date=" + date +
                    ", uptime=" + uptime +
                    ", users=" + users +
                    ", avg1=" + avg1 +
                    ", avg2=" + avg2 +
                    ", avg3=" + avg3 +
                    ", max=" + max +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            InstanceNodeLoadAvg that = (InstanceNodeLoadAvg) o;
            return nodeId.equals(that.nodeId) && date.equals(that.date);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nodeId, date);
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }

        public String getNodeId() {
            return nodeId;
        }

        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public long getUptime() {
            return uptime;
        }

        public void setUptime(long uptime) {
            this.uptime = uptime;
        }

        public int getUsers() {
            return users;
        }

        public void setUsers(int users) {
            this.users = users;
        }

        public double getAvg1() {
            return avg1;
        }

        public void setAvg1(double avg1) {
            this.avg1 = avg1;
        }

        public double getAvg2() {
            return avg2;
        }

        public void setAvg2(double avg2) {
            this.avg2 = avg2;
        }

        public double getAvg3() {
            return avg3;
        }

        public void setAvg3(double avg3) {
            this.avg3 = avg3;
        }
    }
}
