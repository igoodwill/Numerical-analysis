print 'Expression: '
def expression = System.in.newReader().readLine()

print 'a: '
def a = System.in.newReader().readLine().toDouble()

print 'b: '
def b = System.in.newReader().readLine().toDouble()

print 'Precision: '
def precision = System.in.newReader().readLine().toDouble()

print 'Solution: '
println findSolution(expression, a, b, precision)

def evaluateExpression(String expression, double x) {
    def function = evaluate(/
        import static java.lang.Math.*
        {x -> $expression}
    /)
    (double) function(x)
}

def findSolution(String expression, double a, double b, double precision) {
    def solution = (b - a) / 2 + a
    def fa = evaluateExpression(expression, a)
    def fb = evaluateExpression(expression, b)
    if (fa == 0) {
        a
    }

    if (fb == 0) {
        b
    }

    if (Math.signum(fa) != Math.signum(fb)) {
        null
    }

    while (b - a > precision) {
        fa = evaluateExpression(expression, a)
        fb = evaluateExpression(expression, b)
        if (Math.signum(fa) == Math.signum(fb)) {
            a = solution
        } else {
            b = solution
        }

        solution = (b - a) / 2 + a
    }

    solution
}
