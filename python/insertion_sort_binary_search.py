import math as m


class InsertionSort:

    def element_swap(self, iarr, from_elem, to_elem):
        tmp = iarr[from_elem]
        iarr[from_elem] = iarr[to_elem]
        iarr[to_elem] = tmp
        return iarr

    def swap_and_shift_right(self, iarr, from_elem, to_elem):
        tmp = iarr[to_elem]
        iarr[to_elem] = iarr[from_elem]
        to_elem += 1

        while to_elem <= from_elem:
            otmp = iarr[to_elem]
            iarr[to_elem] = tmp
            tmp = otmp
            to_elem += 1

        return iarr

    def binary_search(self, iarr, start, end, value):
        mid = 0

        while start <= end:
            mid = m.floor(start + ((end - start) / 2))
            # Find the mid point, compare the element at mid-point
            # to the element at pointer - 1
            # look to the right first, does our element fall between mid and mid + 1?
            # if yes we're done.
            # else 'look' to the left, if mid = 0
            if iarr[mid] > value > iarr[mid - 1]:
                return mid
            elif iarr[mid] > value > iarr[mid - 1]:
                return mid - 1
            elif iarr[mid] < value:
                start = mid + 1
            else:
                end = mid - 1

        return mid

    def insertion_sort(self, iarr):
        pointer = 1

        # Walk through each element of the array, starting at index 1 (second element)
        while pointer < len(iarr):

            # If the element being examined is < than the element to its left
            # then the current element needs to be moved into the left hand
            # sorted array
            if iarr[pointer] < iarr[pointer - 1]:
                if pointer - 1 == 0:
                    iarr = self.element_swap(iarr, pointer, pointer - 1)
                else:
                    # Using binary search find where the element belongs
                    # It will never belong off the right edge of the sorted sub-array
                    # because it is already there.
                    # it will belong either in position 0 or between two existing elements
                    # Once we find where it goes shift right every element in the sorted array
                    # so that we end with the element at position pointer - 1 now at position
                    # pointer.
                    insert_at = self.binary_search(iarr, 0, pointer - 1, iarr[pointer])
                    iarr = self.swap_and_shift_right(iarr, pointer, insert_at)

            pointer += 1

        return iarr



isort = InsertionSort()
arr = [100, 90, 87, 52, 67, 77, 99]
print(isort.insertion_sort(arr))

# arr = [2, 5, 7, 9, 3, 4]
# print(insertion_sort.swap_and_shift_right(arr, 4, 1))
