from multiprocessing import Pool
import os
import time
import pyhip
import math  # For comparing to actual sine function
inc = 10**-2  # Change increment for testing a function
incX = 10**-6  # Change increment for changing the exponent
# -3 and -8 is good for overnight, -2 and -4 is good for a quick run
x = 0  # X value
exp = 1.6 + inc  # Exponent starting value
pi = math.pi
pii = pi/2
repeats = int(pi/inc) * 1  # Repeats for main repeat
repeats2 = 1/inc  # Repeats for
n1 = 0
n2 = 0
n3 = 0
RunD = []
Diff = []
ExpS = []
# (((((x%pi)-pii)**exp)/(pii**exp)) * -1) + 1

def calculate(exp_start, exp_end, printable=False):
    exponent = exp_start
    Diff = []
    ExpS = []
    while exponent < exp_end:
        RunD = []
        x = 1.5
        while x < pi:
            n1 = (((((x % pi)-pii)**exponent)/(pii**exponent)) * -1) + 1
            if type(n1) != complex:
                n1 = float(abs(abs(n1) - abs(math.sin(x))) * 100)
                RunD.append(n1)
            x = x+inc
    # Find average of list and save to Diff
        ix1 = 0
        n1 = 0
        while ix1 < len(RunD):
            n1 = n1 + RunD[ix1]
            ix1 = ix1 + 1
        n1 = n1 / len(RunD)
        Diff.append(n1)
        ExpS.append(exponent)
        if printable:
            print(str(int((40 - (2-exponent)*100)*5)) + "% | " + "Exponent:" + str(round(exponent, 6)) + " | Accuracy: " + str(round(n1, 6)))
        exponent = exponent + incX
    return (Diff, ExpS)

def seperateChunks(min, max, size=0):
    if size == 0:
        size = (max - min) / os.cpu_count()
    chunks = int((max - min) / size)
    output = []
    currentLocation = min
    for i in range(chunks):
        output.append((currentLocation, currentLocation + size))
        currentLocation += size
    return output

if __name__ == '__main__':
    startTime = time.time_ns()
    arguments = seperateChunks(1.6, 1.8)
    print(f"Created {arguments.__len__()} processes, {(arguments[0][1] - arguments[0][0]) / incX} interations each")
    results = Pool().starmap(calculate, arguments)
    Diff = []
    ExpS = []
    for i in results:
        for item in i[0]:
            Diff.append(item)
        for item in i[1]:
            ExpS.append(item)
    ix1 = 0
    n3 = 10**100
    while ix1 < len(Diff):
        if Diff[ix1] < n3:
            n3 = Diff[ix1]
        ix1 = ix1 + 1
    n1 = round(ExpS[Diff.index(n3)], 6)
    print()
    print(f"Exponent: {n1}")
    print(f"Accuracy: {n3}")
    print(f"Took {(time.time_ns() - startTime) / 1000000000} seconds")
