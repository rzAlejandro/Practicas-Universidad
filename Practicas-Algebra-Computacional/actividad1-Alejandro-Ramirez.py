#Primera forma:

def coger_cifra(n,desp):
    aux = n // (10**desp)
    aux = aux%10
    return aux

n = 1
producto = 1
x = 1
c = 1
aux = 1
cota = 1000000
cont = 0
while n <= cota:
    if x//aux>=10: #Pasamos de numero con c cifras a c+1 cifras
        c += 1
        aux = aux*10
    cont += c
    if cont >= n: #El numero actual contiene la cifra que debemos operar
        m = coger_cifra(x,cont - n)
        producto = producto * m
        n = n*10  
    x += 1
print(producto)


#Segunda forma: concatenando a string
numero = "0"
cont = 1
cota = 1000000
while cont <= cota:
    numero += str(cont)
    cont += 1
posiciones=[1,1e1,1e2,1e3,1e4,1e5,1e6]
x = 1
for i in range(0,len(posiciones)):
    x *= int(numero[int(posiciones[i])])
print(x)