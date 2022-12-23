import math

#Adjustable
samples_test = 1000  # <=100000000
max_repeats = 5000  # >124
learning_rate = 2  #multiplier
#Constants
pi = math.pi
pi2 = pi/2


#Function that computes my custom sine
def compute_sine(x, exponent):
    n1 = ((abs((x % pi) - pi2) ** exponent) / (pi2 ** exponent)) * -1 + 1
    return n1

#Finds the accuracy of an exponent over array of x values
def compute_exponent(exponent):
    x_increment = pi / samples_test
    differences = [0] * samples_test
    x = 0
    for i in range(samples_test):
        x += x_increment
        differences[i] = abs(compute_sine(x, exponent) - math.sin(x))
    return find_average_of_array(differences)

#Finds the average of numbers in the array
def find_average_of_array(input_):
    total = 0
    for a in input_:
        if a == a:
            total += a
    total /= len(input_)
    return total

#Adjusts exponent in order to optimize for best possible exponent
def gradient_ascent(starting_exp):
    exponent = starting_exp
    exp_increment = 0.01
    exp_testing = exponent
    result1 = 0
    result2 = 0
    was_going_down = True

    for i in range(max_repeats):
        result1 = compute_exponent(exp_testing)
        if was_going_down:
            exp_testing = exponent + exp_increment
        else:
            exp_testing = exponent - exp_increment
        result2 = compute_exponent(exp_testing)

        if exponent == exp_testing:  # if there is no change in the exponent
            i += max_repeats

        if result1 > result2:  # if currently going up
            if was_going_down:
                exponent = exp_testing
            else:  # was going down
                exp_increment /= learning_rate  # go up, but slower
                exponent = exp_testing + exp_increment
            was_going_down = True
        else:  # if currently going down
            if was_going_down:
                exp_increment /= learning_rate  # go down, but slower
                exponent = exp_testing - exp_increment
            else:  # was going down
                exponent = exp_testing
            was_going_down = False

    print(f"Best exponent is: {exponent} with an accuracy of: {100 - (compute_exponent(exponent) * 100)}%")

gradient_ascent(1)
