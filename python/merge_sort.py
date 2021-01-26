import math as m


class MergeSort:

    def merge_sort(self, arr):

        if len(arr) <= 1:
            return arr

        mid = m.floor(len(arr) / 2)

        left_arr = arr[:mid]
        right_arr = arr[mid: len(arr)]

        left_arr = self.merge_sort(left_arr)
        right_arr = self.merge_sort(right_arr)

        print(' calling merge on left_arr: ', left_arr, ', right_arr: ', right_arr)

        return self.merge(left_arr, right_arr)

    def merge(self, left_arr, right_arr):
        merged_arr = []
        print('merging left_arr: ', left_arr, ' to right_arr: ', right_arr)

        left_pointer = 0
        right_pointer = 0

        while left_pointer < len(left_arr) and right_pointer < len(right_arr):
            if left_arr[left_pointer] <= right_arr[right_pointer]:
                merged_arr.append(left_arr[left_pointer])
                left_pointer += 1
            else:
                merged_arr.append(right_arr[right_pointer])
                right_pointer += 1

        while left_pointer < len(left_arr):
            merged_arr.append(left_arr[left_pointer])
            left_pointer += 1

        while right_pointer < len(right_arr):
            merged_arr.append(right_arr[right_pointer])
            right_pointer += 1

        print(' merged: ', merged_arr)

        return merged_arr


arr = [53, 23, 2, 5, 8, 67, 42, 28, 16]

merges = MergeSort()
print(merges.merge_sort(arr))

