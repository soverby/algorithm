import math as m


class BinaryHeap:

    def __init__(self, arr):
        self.arr = arr

    # def extract_max(self):

    def max_heapify(self, index):
        left_index = (2 * (index + 1)) - 1
        right_index = left_index + 1
        max_leaf_index = len(self.arr) - 1

        if right_index <= max_leaf_index:  # Two leaf nodes
            root_node = self.arr[index]
            left_node = self.arr[left_index]
            right_node = self.arr[right_index]

            print('root:', root_node, ', left_node:', left_node, ', right_node:', right_node)

            nodes = [root_node, left_node, right_node]

            root_node = max(nodes)
            nodes.remove(root_node)
            right_node = max(nodes)
            left_node = min(nodes)

            print('root:', root_node, ', left_node:', left_node, ', right_node:', right_node)

            self.arr[index] = root_node
            self.arr[left_index] = left_node
            self.arr[right_index] = right_node
        else:  # one leaf node
            if self.arr[index] < self.arr[left_index]:
                tmp = self.arr[index]
                self.arr[index] = self.arr[left_index]
                self.arr[left_index] = tmp

    def max_sort(self):
        last_root_index = m.floor(len(self.arr) / 2) - 1

        for index in range(last_root_index, -1, -1):
            self.max_heapify(index)

        return self.arr


arr = [1, 5, 12, 16, 23, 7, 14]

heap = BinaryHeap(arr)

sorted_arr = heap.max_sort()

print(sorted_arr);

