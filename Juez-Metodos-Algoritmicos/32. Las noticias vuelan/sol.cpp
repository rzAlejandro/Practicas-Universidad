// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include "Grafo.h"
#include "queue_eda.h"
#include <vector>

enum tColor { WHITE, GREY, BLACK };

void bfs(int ver, Grafo const & grafo, std::vector<tColor> & marcas, std::vector<int> & compconexas, std::vector<int> & sol) {
	marcas[ver] = GREY;
	compconexas[ver] = ver;
	int comp = 1;
	queue <int> q;
	q.push(ver);
	while (!q.empty()) {
		int x = q.front();
		q.pop();
		std::vector<int> ad = grafo.ady(x);
		int i = 0;
		while (i < ad.size()) {
			int k = ad[i];
			if (marcas[k] == WHITE) {
				marcas[k] = GREY;
				q.push(k);
				compconexas[k] = ver;
				comp++;
			}
			++i;
		}
		marcas[x] = BLACK;
	}
	sol[ver] = comp;
}

// función que resuelve el problema
std::vector<int> resolver(Grafo const & grafo) {
	int n = grafo.V();
	std::vector<int> compconexas(n,n+1);
	std::vector<tColor> marcas(n, WHITE);
	std::vector<int> sol(n);
	int ver = 0;
	while (ver < n) {
		if (marcas[ver] == WHITE) {
			bfs(ver, grafo, marcas, compconexas, sol);
		}
		else{
			sol[ver] = sol[compconexas[ver]];
		}
		ver++;
	}
	return sol;
}

// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int N, M, num, n, n1;
	std::cin >> N >> M;
	if (!std::cin)
		return false;
	Grafo grafo(N);
	for (int i = 0; i < M; ++i) {
		std::cin >> num;
		if (num != 0) {
			std::cin >> n;
			for (int j = 0; j < num-1; ++j) {
				std::cin >> n1;
				grafo.ponArista(n-1, n1-1);
			}
		}	
	}
	std::vector<int> sol;
	sol = resolver(grafo);

	// escribir sol
	for (int i = 0; i < sol.size(); ++i) {
		std::cout << sol[i] << ' ';
	}
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