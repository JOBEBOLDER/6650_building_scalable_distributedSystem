#!/bin/bash

# 进入 LeetCode 代码存放的目录
cd ~/LeetCode

# 拉取最新代码
git pull origin main

# 添加所有新代码
git add .

# 提交代码，使用当天日期作为 commit message
git commit -m "Daily LeetCode commit - $(date '+%Y-%m-%d')"

# 推送到 GitHub
git push origin main

# 输出成功信息
echo "LeetCode code committed successfully!"