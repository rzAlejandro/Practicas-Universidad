# -*- coding: utf-8 -*-
"""
Created on Mon Oct 25 09:22:22 2021

@author: aleja
"""

import random
from math import ceil,log

def multiplicar_mod (a , b , N ): # 0 <= a,b < N, N >= 1
    c = a * b
    c %= N
    return c

def potencia_mod (a , k , N ): # 0 <= a < N, k >= 0 , N >= 2
    if k == 0: # caso base (k = 0)
        r = 1 # convencion : 0^0 = 1
    elif k % 2 == 0: # k es par (k > 0)
        r = potencia_mod (a , k //2 , N )
        r = multiplicar_mod (r , r , N )
    else : # k es impar (k > 0)
        r = potencia_mod (a , k -1 , N )
        r = multiplicar_mod (a , r , N )
    return r

def criba(n):
   l = [2] * (n+1)   # 2 = aun no se si es primo o compuesto
   l[0] = l[1] = 0   # indico que 0 y 1 no son primos
   i = 2             # empiezo con el indice 2
   while i <= n:
      if l[i] == 2:  # si no lo he tachado
         l[i] = 1    # lo marco como primo
         j = 2*i        # busco los indices j=2i,3i,4i,...
         while j <= n:  # hasta llegar a n y los marco
            l[j] = 0    # como compuestos
            j += i
      i += 1
   return l

def gcd_binario (x , y ): # (x,y) != (0 ,0)
    x = abs( x )
    y = abs( y )
    xespar = x %2 == 0
    yespar = y %2 == 0
    if x == 0: # caso base : gcd (0 ,y)=y
        m = y
    elif y == 0: # caso base : gcd(x ,0)= x
        m = x
    elif xespar and yespar :    
        m = 2 * gcd_binario ( x //2 , y //2)
    elif xespar :
        m = gcd_binario ( x //2 , y )
    elif yespar :
        m = gcd_binario (x , y //2)
    elif x > y :
        m = gcd_binario (y , x - y )
    else :
        m = gcd_binario (x , y - x )
    return m

def pollard (n,b):
    #Elegir de forma aleatoria a:
    a = random.randint(1,n-1)
    #Paso 1
    d = divmod(n,a)
    if d[1] == 0:
        return (a,d[0])
    #Paso 2
    primos = criba(b)
    for i in range(len(primos)):
        if primos[i] == 1:
            l = ceil(log(n,i))
            k = i**l
            a = potencia_mod(a,k,n)
    a = a-1
    a = gcd_binario(a,n)
    if a != 0:
        return (a,n/a)
    return pollard(n)
    
print(pollard(1542201487980564464479858919567403438179217763219681634914787749213,100))