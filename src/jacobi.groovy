def reader = System.in.newReader()

print 'Matrix size: '
def size = reader.readLine().toInteger()

println 'Matrix: '
def matrix = new double[size][size + 1]
for (int i = 0; i < size; i++) {
    def row = reader.readLine().split(' ')
    for (int j = 0; j < row.length; j++) {
        matrix[i][j] = row[j].toDouble()
    }
}

def solution = findSolution(matrix)
println "Solution: $solution"

static def findSolution(double[][] matrix) {
    def exp = 1
    def x = new double[matrix.length]
    while (exp > 0) {
        def tmp_x = new double[matrix.length]
        for (def i = 0; i < matrix.length; i++) {
            tmp_x[i] = 0.0
            for (def j = 0; j < matrix.length; j++) {
                if (i != j) {
                    tmp_x[i] = tmp_x[i] + (matrix[i][j] * x[j])
                }
            }
            tmp_x[i] = (matrix[i][matrix.length] - tmp_x[i]) / matrix[i][i]
        }

        exp = 0

        for (def i = 0; i < matrix.length; i++) {
            if (Math.abs(x[i] - tmp_x[i]) > exp) {
                exp = Math.abs(x[i] - tmp_x[i])
            }
            x[i] = tmp_x[i]
        }
    }

    return x
}
