import java.util.function.BiFunction

print 'Expression: '
def expression = System.in.newReader().readLine()

print 'a: '
def a = System.in.newReader().readLine().toDouble()

print 'b: '
def b = System.in.newReader().readLine().toDouble()

print 'N: '
def n = System.in.newReader().readLine().toInteger()

print 'Rectangle solution: '
println findRectangleSolution(expression, a, b, n)

print 'Trapezoid solution: '
println findTrapezoidSolution(expression, a, b, n)

print 'Simpson solution: '
println findSimpsonSolution(expression, a, b, n)

def evaluateExpression(String expression, double x) {
    def function = evaluate(/
        import static java.lang.Math.*
        {x -> $expression}
    /)
    (double) function(x)
}

def findRectangleSolution(String expression, double a, double b, int n) {
    findSolution((c, d) -> computeRectangle(expression, c, d), a, b, n)
}

def findTrapezoidSolution(String expression, double a, double b, int n) {
    findSolution((c, d) -> computeTrapezoid(expression, c, d), a, b, n)
}

def findSimpsonSolution(String expression, double a, double b, int n) {
    findSolution((c, d) -> computeSimpson(expression, c, d), a, b, n)
}

def computeRectangle(String expression, double a, double b) {
    (b - a) * evaluateExpression(expression, (a + b) / 2)
}

def computeTrapezoid(String expression, double a, double b) {
    (b - a) * (evaluateExpression(expression, a) + evaluateExpression(expression, b)) / 2
}

def computeSimpson(String expression, double a, double b) {
    (b - a) * (evaluateExpression(expression, a) +
            4 * evaluateExpression(expression, (a + b) / 2) + evaluateExpression(expression, b)) / 6
}

static def findSolution(BiFunction<Double, Double, Double> computeFunction, double a, double b, int n) {
    def dx = (b - a) / n
    def result = 0
    for (int i = 0; i < n; i++) {
        def c = a + dx * i
        result += computeFunction.apply(c, c + dx)
    }

    result
}
