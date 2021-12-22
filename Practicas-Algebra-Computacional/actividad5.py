# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""
import math
cota = 10

def gcd_binario(x,y):
    x = abs ( x )
    y = abs ( y )
    xespar = x %2 == 0
    yespar = y %2 == 0
    if x == 0: # caso base : gcd (0 , y )= y
        m = y
    elif y == 0: # caso base : gcd (x ,0)= x
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


def es_primo(n):
    x = 2
    primo = True
    while x <= math.sqrt(n) and primo:
        if n % x == 0:
            primo = False
        x += 1
    return primo
    
def multiplicar_mod (a , b , N ): # 0 <= a , b < N , N >= 1
    c = a * b
    c %= N
    return c


def potencia_mod(a,k,n):
    if k == 0: # caso base ( k = 0)
        r = 1 # convencion : 0^0 = 1
    elif k % 2 == 0: # k es par ( k > 0)
        r = potencia_mod (a , k //2 , n )
        r = multiplicar_mod (r , r , n )
    else : # k es impar ( k > 0)
        r = potencia_mod (a , k -1 , n )
        r = multiplicar_mod (a , r , n )
    return r
#Forma1
def carmichael():
    sol = []
    cont = 0
    n = 1
    while cont < cota:
        if not es_primo(n): # No primos
            #Ver si es pseudoprimo (hay que comprobar para 2<=a<n)
            stop = False
            a = 2
            while (not stop) and a<n:
                if gcd_binario(a,n) == 1:
                    if potencia_mod(a,n-1,n) != 1:
                        stop = True
                a += 1
            if not stop:
                sol.append(n)
                cont += 1
        n += 1
    return sol



print(carmichael())