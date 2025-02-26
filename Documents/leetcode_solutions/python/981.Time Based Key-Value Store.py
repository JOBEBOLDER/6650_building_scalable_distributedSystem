class TimeMap:
    #design a datastructure
    #time:O nlogn
    #space:On

    def __init__(self):
        self.dic = collections.defaultdict(list)

    def set(self, key: str, value: str, timestamp: int) -> None:
        self.dic[key].append([timestamp, value])


    def get(self, key: str, timestamp: int) -> str:
        arr = self.dic[key]
        n = len(arr)

        left = 0
        right = n

        while left < right:
            mid = left + (right - left) // 2
            # 说明 mid 可能是解，但看看右边是否有更大的合法值
            if arr[mid][0] <= timestamp:
                left = mid + 1
            elif arr[mid][0] > timestamp:
                right = mid# mid 太大了，缩小右边界

        return "" if right == 0 else arr[right - 1][1]
'''
整体时间复杂度

如果你进行 M 次 set 操作 和 M 次 get 操作：
	•	set 方法每次是 O(1)，M 次 set 操作 = O(M)
	•	get 方法每次是 O(logN)，M 次 get 操作 = O(M logN)

所以总的时间复杂度为：
O(M + M \log N) = O(M \log N)




	•	self.dic 维护了一个字典，key 是字符串，value 是时间戳-值的列表。
	•	假设最多有 K 个不同的 key，每个 key 存储 N 个值，则：
	•	字典占用 O(K)
	•	每个 key 对应的列表占用 O(N)
	•	总空间复杂度： O(KN)
'''