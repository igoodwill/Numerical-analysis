def reader = System.in.newReader()

print 'N: '
def n = reader.readLine().toInteger()

println 'Values: '
def values = new double[n]
for (int i = 0; i < n; i++) {
    values[i] = reader.readLine().toDouble()
}

while (true) {
    print 'x: '
    def line = reader.readLine()
    if (line.isEmpty()) {
        break
    }

    def x = line.toDouble()
    def solution = findSolution(values, x)
    println "Solution: $solution"
}

static def findSolution(double[] values, double x) {
    def n = values.length
    def t = new double[n + 1][n]
    def xs = new double[n]
    for (int i = 0; i < n; i++) {
        xs[i] = Math.cos((2 * i + 1) * Math.PI / (2 * n))
    }

    def tx = new double[n]
    tx[0] = 1
    tx[1] = x
    for (int i = 2; i < n; i++) {
        tx[i] = 2 * x * tx[i - 1] - tx[i - 2]
    }

    def a = new double[n]
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (i == 0) {
                t[i][j] = 1
            } else if (i == 1) {
                t[i][j] = xs[j]
            } else {
                t[i][j] = 2 * xs[j] * t[i - 1][j] - t[i - 2][j]
            }
        }

        for (int j = 0; j < n; j++) {
            a[i] += values[j] * t[i][j]
        }

        if (i > 0) {
            a[i] *= 2
        }

        a[i] /= n
    }

    double result = 0
    for (int i = 0; i < n; i++) {
        result += a[i] * tx[i]
    }

    return result
}
