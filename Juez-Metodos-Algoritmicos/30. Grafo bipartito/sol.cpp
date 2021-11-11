// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include "Grafo.h"
#include "queue_eda.h"
#include <vector>
#include <string>
enum tColor{WHITE,GREY,BLACK};

bool isPar(int x) {
	return x % 2 == 0;
}
// función que resuelve el problema
bool bfs(int ver,Grafo const & grafo, std::vector<tColor> & marcas, std::vector<int> & niveles, std::vector<int> & secVisitas, std::vector<int> & edgeTo) {
	marcas[ver] = GREY;
	secVisitas.push_back(ver);
	queue <int> q;
	q.push(ver);
	bool ok = true;
	while (!q.empty() && ok) {
		int x = q.front();
		q.pop();
		std::vector<int> ad = grafo.ady(x);
		int i = 0;
		while (i < ad.size() && ok) {
			int k = ad[i];
			if (marcas[k] == WHITE) {
				marcas[k] = GREY;
				secVisitas.push_back(k);
				q.push(k);
				edgeTo[k] = x;
				niveles[k] = niveles[x] + 1;
			}
			else
				ok = (isPar(niveles[x]) && !isPar(niveles[k])) or (!isPar(niveles[x]) && isPar(niveles[k]));
			++i;
		}
		marcas[x] = BLACK;
	}
	return ok;
}

bool  BFS(Grafo const & grafo, std::vector<int> & secVisitas, std::vector<int> & edgeTo) {
	int n = grafo.V();
	std::vector<int> niveles(n);
	std::vector<tColor> marcas(n, WHITE);
	int ver = 0;
	bool ok = true;
	while (ver < n && ok) {
		if (marcas[ver] == WHITE) {
			niveles[ver] = 0;
			ok = bfs(ver, grafo, marcas, niveles, secVisitas, edgeTo);
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
	Grafo  grafo (n);
	for (int i = 0; i < a; ++i) {
		std::cin >> u >> v;
		grafo.ponArista(u, v);
	}
	std::vector<int> secVisitas;
	std::vector<int> edgeTo(n);
	bool sol = BFS(grafo, secVisitas, edgeTo);

	// escribir sol
	if (sol)
		std::cout << "SI" << '\n';
	else std::cout << "NO" << '\n';

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