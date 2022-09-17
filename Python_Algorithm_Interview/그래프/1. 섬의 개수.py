class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
       
        def dfs(x,y) :
            limit_x = len(grid)
            limit_y = len(grid[0])

            if x < 0 or x >= limit_x or y < 0 or y >=limit_y :
                return False

            if grid[x][y] == "1" :
                grid[x][y] = "0"
                dfs(x-1,y)
                dfs(x,y-1)
                dfs(x+1,y)
                dfs(x,y+1)

                return True

            return False
        
        result =0

        for i in range(len(grid)):
            for j in range(len(grid[0])) :
                if dfs(i,j) == True:
                    result +=1
                    
        return result
        
        