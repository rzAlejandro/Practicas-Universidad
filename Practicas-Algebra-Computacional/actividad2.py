m = 100
sol = [1]*m
stop = False
turno = 0
cont = 0

while not stop:
    
    #Ejecutar turno correspondiente
    
    pos = turno%m
    for i in range(sol[pos]): 
        turno = (turno + 1)%m
        sol[turno] += 1
        sol[pos] -= 1
    i = 0
    
    #Comprobar si es configuración inicial:
    
    #También se podría comprobar con otra lista (mediante sol == lista), 
    #pero la complejidad temporal va a ser la misma y ocupa más espacio en memoria
    
    while i < m and not stop: 
        if sol[i] != 1:
            stop = True
        i += 1
    stop = not stop
    cont += 1
    
print("El numero de turnos es " + str(cont))
