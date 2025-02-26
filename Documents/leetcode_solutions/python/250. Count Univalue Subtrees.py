# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def __init__(self):
        self.count = 0
    def countUnivalSubtrees(self, root: Optional[TreeNode]) -> int:
        self.dfs(root)
        return self.count

    def dfs(self, root:Optional[TreeNode]):
        if root is None:
            return True

        # Check if left and right subtrees are uni-value
        _is_left_uni = self.dfs(root.left)
        _is_right_uni = self.dfs(root.right)

        if not _is_left_uni or not _is_right_uni:
            return False

        if root.left and root.left.val != root.val:
            return False

        if root.right and root.right.val != root.val:
            return False

        self.count += 1
        return True



