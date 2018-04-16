import scipy.special as sp
import sys
m=float(sys.argv[1])
# m=10
min=0.01
lst=[]
i=0;
while True:
    if m==0:
        lst.append(1.0)
        break
    if abs(sp.jn(i, m))<min and (abs(sp.jn(i, m) > 0)):
        break
    lst.append("{0:.2f}".format(sp.jn(i,m)))
    i=i+1
print(lst)
