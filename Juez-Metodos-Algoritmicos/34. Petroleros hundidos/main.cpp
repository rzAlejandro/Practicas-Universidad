// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include <vector>
#include "Grafo.h"
#include "queue_eda.h"
#include "ConjuntosDisjuntos.h"
#include <string>
#include <sstream>

const std::vector<int> incF = {-1,-1,0,1,1,1,0,-1 };
const std::vector<int> incC = { 0,1,1,1,0,-1,-1,-1 };


enum tColor { WHITE, GREY, BLACK };

bool existe(int alto, int ancho, int i, int j) {
	return 0 <= i && i < alto && 0 <= j && j < ancho;
}



void bfs(Grafo grafo, std::vector<bool> const & estadoCasillas, ConjuntosDisjuntos & conjuntos,int & maximo,int ver,std::vector<bool> & marcas) {
	marcas[ver] = true;
	queue <int> q;
	q.push(ver);
	while (!q.empty()) {
		int x = q.front();
		q.pop();
		std::vector<int> ad = grafo.ady(x);
		int i = 0;
		while (i < ad.size()) {
			int k = ad[i];
			marcas[k] = true;
			if(marcas[k]==WHITE)
				q.push(k);
			if (estadoCasillas[x] && estadoCasillas[k]) {
				if (conjuntos.buscar(x) != conjuntos.buscar(k)) {
					conjuntos.unir(x, k);
					if (conjuntos.tam(conjuntos.buscar(x)) > maximo)
						maximo = conjuntos.tam(conjuntos.buscar(x));
				}
			}
			++i;
		}
		

	}
}



int resolver1(Grafo const & grafo, std::vector<bool> const & estadoCasillas, ConjuntosDisjuntos & conjuntos,int f,int c,int alto,int ancho,int maxIni) {
	int n = grafo.V();
	int maximo = maxIni;
	for (int i = 0; i < 8; ++i) {
		int posFila = f + incF[i];
		int posCol = c + incC[i];
		if (existe(alto, ancho, posFila, posCol) && estadoCasillas[ancho*posFila + posCol]) {
			if (conjuntos.buscar(ancho*f + c) != conjuntos.buscar(ancho*posFila + posCol)) {
				conjuntos.unir(ancho*f + c, ancho*posFila + posCol);
				if (conjuntos.tam(conjuntos.buscar(ancho*f + c)) > maximo)
					maximo = conjuntos.tam(conjuntos.buscar(ancho*f + c));
			}
		}
	}
	return maximo;

}



// función que resuelve el problema
int resolver(Grafo const & grafo, std::vector<bool> const & estadoCasillas, ConjuntosDisjuntos & conjuntos) {
	int maximo;
	int ver = 0;
	std::vector<bool> marcas(grafo.V(), false);
	while (ver < grafo.V()) {
		if (!marcas[ver] && estadoCasillas[ver]) {
			bfs(grafo, estadoCasillas, conjuntos, maximo,ver,marcas);
		}
		
		ver++;
	}
	
	return maximo;
}

Grafo construirGrafo (std::vector<std::vector<char>> const & matriz) {
	int alto = matriz.size();
	int ancho = matriz[0].size();
	std::vector<bool> marcas(alto*ancho, false);
	Grafo grafo(alto*ancho);
	for (int i = 0; i < alto; ++i) {
		for (int j = 0; j < ancho; ++j) {
			for (int k = 0; k < 8; ++k) {
				int PosFila = i + incF[k];
				int PosCol = j + incC[k];
				if (existe(alto, ancho,PosFila,PosCol) && !marcas[ancho * PosFila + PosCol])
					grafo.ponArista(ancho * i + j, ancho * PosFila + PosCol);
			}
			marcas[ancho*i + j] = true;
		}
		
	}
	return grafo;

}

// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int F, C, N, f, c;
	char ch;
	std::cin >> F >> C;
	if (!std::cin)
		return false;

	std::vector<bool>estadoCasillas(F*C);
	std::vector<std::vector<char>> matriz(F);
	std::cin.get(ch);//salto de linea
	for(int i=0;i<F;++i){
		for(int j=0;j<C;++j){
			std::cin.get(ch);
			matriz[i].push_back(ch);
			if (ch == ' ')
				estadoCasillas[C*i + j] = false;
			else estadoCasillas[C*i + j] = true;
		}
		std::cin.get(ch);//Salto de linea
	}
	Grafo grafo = construirGrafo(matriz);
	ConjuntosDisjuntos conjuntos(F*C);
	int maxIni = resolver(grafo,estadoCasillas,conjuntos);
	std::cout << maxIni<<' ';
	std::cin >> N;

for (int i = 0; i < N; ++i) {
		std::cin >> f >> c;
		estadoCasillas[C*(f-1) + c - 1] = true;
		int sol = resolver1(grafo, estadoCasillas, conjuntos,f-1,c-1,F,C,maxIni);
		maxIni = sol;
		std::cout << sol<<' ';
	}

	
	// escribir sol
	std::cout << '\n';

	return true;

}

int main() {
	// Para la entrada por fichero.
	// Comentar para acepta el reto
#ifndef DOMJUDGE
    std::ifstream in("datos.txt");
	auto cinbuf = std::cin.rdbuf(in.rdbuf()); //save old buf and redirect std::cin to casos.txt
#endif 


	while (resuelveCaso())
		;


	// Para restablecer entrada. Comentar para acepta el reto
#ifndef DOMJUDGE // para dejar todo como estaba al principio
   std::cin.rdbuf(cinbuf);
	system("PAUSE");
#endif

	return 0;
}