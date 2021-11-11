// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include "Grafo.h"
#include "queue_eda.h"
#include <vector>
#include <climits>
#include <algorithm>

enum tColor { WHITE, GREY, BLACK };


bool isPar(int x) {
	return x % 2 == 0;
}
// función que resuelve el problema
bool bfs(int ver, Grafo const & grafo,int & m, std::vector<tColor> & marcas, std::vector<int> & niveles) {
	niveles[ver] = 0;
	int cont1 = 0;
	int cont2 = 0;
	marcas[ver] = GREY;
	queue <std::pair<int,bool>> q;
	q.push({ ver,true });
	bool ok = true;
	while (!q.empty() && ok) {
		std::pair<int,bool> x = q.front();
		q.pop();
		if (x.second)
			cont1++;
		else cont2++;
		std::vector<int> ad = grafo.ady(x.first);
		int i = 0;
		while (i < ad.size() && ok) {
			int k = ad[i];
			if (marcas[k] == WHITE) {
				marcas[k] = GREY;
				q.push({ k,!x.second });
				niveles[k] = niveles[x.first] + 1;
			}
			else
				ok = (isPar(niveles[x.first]) && !isPar(niveles[k])) or (!isPar(niveles[x.first]) && isPar(niveles[k]));
			++i;
		}
		marcas[x.first] = BLACK;
	}
	m = std::min(cont1, cont2);
	return ok;
}

bool  BFS(Grafo const & grafo,int & minimo) {
	int n = grafo.V();
	std::vector<tColor> marcas(grafo.V(), WHITE);
	std::vector<int> niveles(grafo.V());
	int ver = 0;
	int minAux = 0;
	bool ok = true;
	while (ver < n && ok) {
		if (marcas[ver] == WHITE) {
			ok = bfs(ver, grafo, minAux, marcas, niveles);
			minimo += minAux;
		}
		ver++;
	}
	return ok;
}


// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int n, a, u, v;
	std::cin >> n >> a;
	if (!std::cin)
		return false;

	Grafo grafo(n);
	for (int i = 0; i < a; ++i) {
		std::cin >> u >> v;
		grafo.ponArista(u-1, v-1);
	}
	
	int minimo = 0;

	// escribir sol
	if (BFS(grafo,minimo))
		std::cout <<minimo << '\n';
	else std::cout << "IMPOSIBLE" << '\n';

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