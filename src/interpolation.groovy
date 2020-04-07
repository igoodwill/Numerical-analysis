def reader = System.in.newReader()

print 'N: '
def n = reader.readLine().toInteger()

println 'Values: '
def values = new HashMap<Double, Double>()
for (int i = 0; i < n; i++) {
    def row = reader.readLine().split(' ')
    values.put(row[0].toDouble(), row[1].toDouble())
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

static def findSolution(Map<Double, Double> values, double x) {
    double result = 0
    double basics_pol

    for (Double key : values.keySet()) {
        basics_pol = 1
        for (Double key2 : values.keySet()) {
            if (key != key2) {
                basics_pol *= (x - key2) / (key - key2)
            }
        }

        result += basics_pol * values.get(key)
    }

    return result
}
