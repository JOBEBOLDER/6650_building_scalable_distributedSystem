class Solution:
    def maxSum(self, grid: List[List[int]], limits: List[int], k: int) -> int:
        a = []
        for row, limits in zip(grid, limits):
            row.sort(reverse=True)
            #extend() 用于 将一个列表的所有元素添加到另一个列表中。
            a.extend(row[:limits])
        a.sort(reverse=True)#	reverse=True：降序排序（大到小）。默认是false，小到大
        return sum(a[:k])