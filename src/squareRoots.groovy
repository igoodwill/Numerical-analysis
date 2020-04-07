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

for (int i = 0; i < size; i++) {
    def check = 0
    for (int j = 0; j < size; j++) {
        check += solution[j] * matrix[i][j]
    }

    println check
}

static def findSolution(double[][] matrix) {
    def size = matrix.length
    def sMatrix = new double[size][size]
    def d = new double[size]
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < i; j++) {
            def s = matrix[i][j]
            for (int l = 0; l < j; l++) {
                s -= sMatrix[i][l] * d[l] * sMatrix[j][l]
            }

            s /= sMatrix[j][j] * d[j]
            sMatrix[i][j] = s
        }

        def p = matrix[i][i]
        for (int j = 0; j < i; j++) {
            p -= d[j] * sMatrix[i][j] * sMatrix[i][j]
        }

        d[i] = Math.signum(p)
        sMatrix[i][i] = Math.sqrt(d[i] * p)
    }

    def y = new double[size]
    for (int i = 0; i < size; i++) {
        def yi = matrix[i][size]
        for (int j = 0; j < i; j++) {
            yi -= sMatrix[i][j] * d[j] * y[j]
        }

        yi /= sMatrix[i][i] * d[i]
        y[i] = yi
    }

    def x = new double[size]
    for (int i = size - 1; i >= 0; i--) {
        def xi = y[i]
        for (int j = i + 1; j < size; j++) {
            xi -= sMatrix[j][i] * x[j]
        }

        xi /= sMatrix[i][i]
        x[i] = xi
    }

    return x
}
