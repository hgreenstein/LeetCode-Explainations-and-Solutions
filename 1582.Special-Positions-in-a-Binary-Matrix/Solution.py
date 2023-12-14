class Solution:
    def numSpecial(self, mat: List[List[int]]) -> int:
        rows, cols = (len(mat), len(mat[0]))
        boolmat = [[True for _ in range(cols)] for _ in range(rows)]
             
        # Check rows
        for i in range(rows):
            if mat[i].count(1) != 1 or sum(mat[i]) != 1:
                boolmat[i] = [False] * cols

        # Check columns
        for j in range(cols):
            column = [mat[i][j] for i in range(rows)]
            if column.count(1) != 1 or sum(column) != 1:
                for row in boolmat:
                    row[j] = False

        # Count valid elements
        result = 0
        for i in range(rows):
            for j in range(cols):
                if mat[i][j] == 1 and boolmat[i][j]:
                    result += 1

        return result

