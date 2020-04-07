print 'Expression: '
def expression = System.in.newReader().readLine()

print 'Derivative: '
def derivative = System.in.newReader().readLine()

print 'Initial approximation: '
def initialApproximation = System.in.newReader().readLine().toDouble()

print 'Precision: '
def precision = System.in.newReader().readLine().toDouble()

print 'Solution: '
println findSolution(expression, derivative, initialApproximation, precision)

def evaluateExpression(String expression, double x) {
    def function = evaluate(/
        import static java.lang.Math.*
        {x -> $expression}
    /)
    (double) function(x)
}

def findSolution(String expression, String derivative, double initialApproximation, double precision) {
    double prev = initialApproximation
    double current = initialApproximation -
            evaluateExpression(expression, initialApproximation) / evaluateExpression(derivative, initialApproximation)
    while (Math.abs(prev - current) > precision) {
        prev = current
        current = current - evaluateExpression(expression, current) / evaluateExpression(derivative, current)
    }

    current
}
