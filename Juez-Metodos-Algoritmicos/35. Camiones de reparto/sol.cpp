// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include "GrafoValorado.h"
#include "queue_eda.h"

enum tColor { WHITE, GREY, BLACK };

bool bfs(int ver, int final, int anchura, GrafoValorado <int> const & grafo){
	bool enc = false;
	std::vector<tColor> marcas(grafo.V(), WHITE);
	marcas[ver] = GREY;
	queue <int> cola;
	cola.push(ver);
	while (!cola.empty() && !enc) {
		int x = cola.front();
		cola.pop();
		std::vector<Arista<int>> Ady = grafo.ady(x);
		for (int i = 0; i < Ady.size() && !enc; ++i) {
			Arista<int> k = Ady[i];
			if (marcas[k.otro(x)] == WHITE && k.valor() >= anchura) {
				if (k.otro(x) == final)
					enc = true;
				else {
					cola.push(k.otro(x));
					marcas[k.otro(x)] = GREY;
				}
			}
		}
		marcas[x] = BLACK;
	}
	return enc;
}



// función que resuelve el problema
bool resolver(int origen,int final, int anchura, GrafoValorado <int> const & grafo) {
	
	return bfs(origen, final, anchura, grafo);

}

// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int V, E, K, u, v, anchura;
	std::cin >> V;
	if (!std::cin)
		return false;
	std::cin >> E;
	GrafoValorado <int> grafo(V);
	for (int i = 0; i < E; ++i) {
		std::cin >> u >> v >> anchura;
		Arista <int> a(u - 1, v - 1, anchura);
		grafo.ponArista(a);
	}

	std::cin >> K;
	for (int i = 0; i < K; ++i) {
		std::cin >> u >> v >> anchura;
		if(resolver(u - 1, v - 1, anchura, grafo))
			std::cout << "SI" << '\n';
		else std::cout << "NO" << '\n';
	}
		

	// escribir sol

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