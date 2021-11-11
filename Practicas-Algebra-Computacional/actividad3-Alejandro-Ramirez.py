#Forma 1: Recursivo
#No llega a 10**6 por la profundidad de la recursion
ganarR = {}
def es_posible_ganar_con_n_piedrasR(n):
    if n<=0:
        return True
    if n in ganarR:
        return ganarR[n]
    ganarR[n] = not es_posible_ganar_con_n_piedrasR(n-1) or not es_posible_ganar_con_n_piedrasR(n-2) or not es_posible_ganar_con_n_piedrasR(n-6)
    return ganarR[n]

#Forma 2: Iterativo
#La idea es la misma, estrategia ganadora si con algún movimiento tu rival no tiene ninguna estrategia ganadora
#Con esta forma si podemos computar el caso 10**6
ganar = {}
mov = [6,2,1]
def es_posible_ganar_con_n_piedras(n):
    k = 1
    while k <= n:
        ganar[k] = False #Como es or no afecta
        for j in range(3):
            if k - mov[j] > 0: # Si es menor o igual que 0 ha perdido, es false y no aporta nada al or. 
                ganar[k] = ganar[k] or not ganar[k - mov[j]]
        k += 1
    return ganar[n] 
print(str(10**6) + ": " + str(es_posible_ganar_con_n_piedras(10**6)))