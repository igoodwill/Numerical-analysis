print 'Expression: '
def expression = System.in.newReader().readLine()

print 'Initial approximation: '
def initialApproximation = System.in.newReader().readLine().toDouble()

print 'Precision: '
def precision = System.in.newReader().readLine().toDouble()

print 'Solution: '
println findSolution(expression, initialApproximation, precision)

def evaluateExpression(String expression, double x) {
    def function = evaluate(/
        import static java.lang.Math.*
        {x -> $expression}
    /)
    (double) function(x)
}

def findSolution(String expression, double initialApproximation, double precision) {
    def prev = initialApproximation
    def current = evaluateExpression(expression, initialApproximation) + initialApproximation
    while (Math.abs(current - prev) > precision) {
        prev = current
        current = evaluateExpression(expression, current) + current
    }

    current
}
