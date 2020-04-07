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
    def size = matrix.length
    def c = new double[size]
    def d = new double[size]
    def x = new double[size]
    c[0] = matrix[0][1] / matrix[0][0]
    d[0] = matrix[0][size] / matrix[0][0]
    for (def i = 1; i < size - 1; i++) {
        def id = matrix[i][i] - c[i - 1] * matrix[i][i - 1]
        c[i] = matrix[i][i + 1] / id
        d[i] = (matrix[i][size] - d[i - 1] * matrix[i][i - 1]) / id
    }

    d[size - 1] = (matrix[size - 1][size] - d[size - 2] * matrix[size - 1][size - 2]) /
            (matrix[size - 1][size - 1] - c[size - 2] * matrix[size - 1][size - 2])

    x[size - 1] = d[size - 1]
    for (int i = size - 2; i >= 0; i--) {
        x[i] = d[i] - c[i] * x[i + 1]
    }

    return x
}
