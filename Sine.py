import math #For comparing to actual sine function
inc = 10**-2 #Change increment for testing a function
incX = 10**-5 #Change increment for changing the exponent
#-3 and -8 is good for overnight, -2 and -4 is good for a quick run
x = 0 #X value
exp = 1.6 + inc #Exponent starting value
pi = 3.141592653589793238
pii = pi/2
repeats = int(pi/inc) * 1 #Repeats for main repeat
repeats2 = 1/inc #Repeats for
n1 = 0
n2 = 0
n3 = 0
RunD = []
Diff = []
ExpS = []
#(((((x%pi)-pii)**exp)/(pii**exp)) * -1) + 1
while exp < 1.8:
    ix1 = 0  #Repeat index 1
    RunD = []
    x = 1.5
    while x < pi:
        n1 = (((((x%pi)-pii)**exp)/(pii**exp)) * -1) + 1
        if type(n1) != complex:
            n1 = float(abs(abs(n1) - abs(math.sin(x))) * 100)
            RunD.append(n1)
        else:
            ix1 = ix1
            #RunD.append(n1.real + n1.imag)
        x = x+inc
        ix1 = ix1+1
    #Find average of list and save to Diff
    ix1 = 0
    n1 = 0
    while ix1 < len(RunD):
        n1 = n1 + RunD[ix1]
        ix1 = ix1 + 1
    n1 = n1 / len(RunD)
    Diff.append(n1)
    ExpS.append(exp)
    print(str(int((40 - (2-exp)*100)*5)) + "% | " + "Exponent:" + str(round(exp,6)) + " | Accuracy: " + str(round(n1,6)))
    exp = exp + incX
ix1 = 0
n3 = 10**100
print()
while ix1 < len(Diff):
    if Diff[ix1] < n3:
        n3 = Diff[ix1]
        #print("Next best is " + str(round(n3,6)) + " | " + str(ix1))
    ix1 = ix1 + 1
n1 = round(ExpS[Diff.index(n3)],6)
print()
print("Exponent: " + str(n1))
print("Accuracy: " + str(n3))