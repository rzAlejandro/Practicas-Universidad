def multiplicar_pot_u(p,exp):     #p esta en Z../<u^2n1 + 1>.
    n1_d,s = len(p), 1  #s: signo por si u^2n1 + k = -u^k (u^2n1 = -1)
    l = [0]*n1_d
    exp = exp % (2*n1_d)   #u^4n1 = 1
    if exp >= n1_d:  #u^2n1 = -1 => u^2n1+k = -u^k 
        exp, s = exp - n1_d, -1
    for i in range(n1_d):
        if i - exp < 0:   #desbordan los exp ultimos elementos, aplicar modulo => pasan restando
            l[i] = -s*p[n1_d - exp + i]
        else:
            l[i] = s*p[i - exp]
    return l

def negaconvolucion_basica(f,g,p): #formula de megaconvolucion para casos base (k = 0,1,2)
    l = []
    for i in range(len(f)):
        s = 0
        for j in range(len(f)):
            if j > i:
                s -= f[j]*g[i + len(f) - j]
            else:
                s += f[j]*g[i - j]
        l.append(s%p)
    return l

def ifft(f,exp,p):
    n2, n1_d = len(f), len(f[0])
    q = fft(f,2*n1_d - exp,p) #xi^-1 = u^exp',  exp'=4n1 - exp
    inv_n2 = pow(n2,-1,p) #Halla la inversa de n2 en Zp. Siempre tiene inversa.
    return [[(q[i][j] * inv_n2)%p for j in range(n1_d)] for i in range(n2)]

def fft(f,exp,p):
    n2, n1_d = len(f) , len(f[0]) #n1_d = 2n1
    if n2 == 1:
        return f
    f_even, f_odd = [[0]*n1_d]*(n2//2) , [[0]*n1_d]*(n2//2)
    for i in range(n2//2):
        f_even[i], f_odd[i]  = f[2*i], f[2*i + 1]
    a_even , a_odd = fft(f_even, 2*exp,p), fft(f_odd, 2*exp,p)   #xi = u^exp => xi**2 = u^(2*exp)
    a = [[0]*n1_d]*n2
    for i in range(n2//2):
        aux = multiplicar_pot_u(a_odd[i], i*exp)
        a[i] = [(a_even[i][j] + aux[j])%p for j in range(n1_d)]
        a[i + n2//2] = [(a_even[i][j] - aux[j])%p for j in range(n1_d)]
    return a

def negaconvolucion(f,g,k1,p):
    n1_d,n2 = len(f[0]),len(f)   #n1_d = 2n1
    exp = n1_d//n2                #u^exp es raiz 2n2-ésima
    v = [multiplicar_pot_u(f[k], exp*k) for k in range(n2)]
    w = [multiplicar_pot_u(g[k], exp*k) for k in range(n2)]
    v_d, w_d = fft(v,2*exp,p), fft(w, 2*exp,p)       #u^2exp es raiz n2-ésima
    r = [[0]*n1_d]*n2
    for i in range(n2): #Recursion
        r[i] = mult_ss_mod(v_d[i],w_d[i],k1,p)
    r_i = ifft(r, 2*exp,p)
    return [multiplicar_pot_u(r_i[i],2*n1_d - i*exp) for i in range(n2)]
    
    
def mult_ss_mod(f,g,k,p):
    c = pow(2,k)
    f,g = f + [0]*(c - len(f)), g + [0]*(c - len(g))
    if k <= 2:
        return negaconvolucion_basica(f,g,p)
    else:
        k1 = k//2
        k2 = k - k1
        n1, n2 = pow(2,k1), pow(2,k2)
        f_t = [[f[x] for x in range(n1*i, n1*(i+1))] + [0]*n1 for i in range(0, n2)]
        g_t = [[g[x] for x in range(n1*i, n1*(i+1))] + [0]*n1 for i in range(0, n2)]
        h_t = negaconvolucion(f_t,g_t,k1 + 1,p)
        h = [(h_t[0][j] - h_t[len(h_t) - 1][n1 + j]) % p for j in range(n1)]
        h += [(h_t[i][j + n1] + h_t[i+1][j]) % p for i in range(len(h_t) - 1) for j in range(n1)]
        return h
       
def mult_pol_mod(f,g,p):
    if len(f) == 0 or len(g) == 0:
        return []
    x, k = 1, 0
    while x <= len(f) + len(g) - 2: #Cogemos k tq 2^k>deg(f) + deg(g)
        x*=2
        k += 1
    h = mult_ss_mod(f,g,k,p) 
    
    #Eliminar 0`s de la derecha de h
    i = len(h) - 1
    while i >= 0 and h[i] == 0:
        i -= 1
    if i<0:
        return []
    return h[:i+1]

