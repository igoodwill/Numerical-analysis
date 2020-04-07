print 'Expression: '
def expression = System.in.newReader().readLine()

print 'x1: '
def x1 = System.in.newReader().readLine().toDouble()

print 'x2: '
def x2 = System.in.newReader().readLine().toDouble()

print 'y1: '
def y1 = System.in.newReader().readLine().toDouble()

print 'y2: '
def y2 = System.in.newReader().readLine().toDouble()

print 'N: '
def n = System.in.newReader().readLine().toInteger()

print 'Number of attempts: '
def attempts = System.in.newReader().readLine().toInteger()

def function = evaluate(/
        import static java.lang.Math.*
        {x, y -> $expression}
    /)

for (int i = 1; i <= attempts; i++) {
    println "Solution #$i: ${findSolution(function, x1, x2, y1, y2, n)}"
}

static def findSolution(function, double x1, double x2, double y1, double y2, int n) {
    def random = new Random()
    def result = 0
    for (int i = 0; i < n; i++) {
        double x = x1 + (x2 - x1) * random.nextDouble()
        double y = y1 + (y2 - y1) * random.nextDouble()

        result += (double) function(x, y)
    }

    result /= n
    result *= (x2 - x1) * (y2 - y1)

    result
}
