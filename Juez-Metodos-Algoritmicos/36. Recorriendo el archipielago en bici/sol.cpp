// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include "GrafoValorado.h"
#include "PriorityQueue.h"
#include <vector>
#include <climits>

struct comparator {
	bool operator()(std::pair<int, int> p1, std::pair<int, int>p2) {
		return p1.second < p2.second;
	}
};
void visitar(GrafoValorado <int> grafo, std::pair<int, int> v, std::vector<int> & distTo, PriorityQueue <std::pair<int, int>, comparator> & vertices, std::vector<bool> marcas) {
	for (Arista<int> a : grafo.ady(v.first)) {
		int w = a.otro(v.first);
		if (!marcas[w] && a.valor()<distTo[w]) {
			distTo[w] = a.valor();
			vertices.push({w,distTo[w]});
		}
	}
}

// función que resuelve el problema
bool resolver(GrafoValorado <int> grafo,int & coste) {
	PriorityQueue <std::pair<int, int>, comparator>vertices;
	std::vector<bool> marcas(grafo.V(), false);
	vertices.push({ 0,0 });
	int cont = 0;
	std::vector<int> distTo(grafo.V(), INT_MAX);
	while (cont < grafo.V() && !vertices.empty()) {
		std::pair<int, int> v = vertices.top();
		vertices.pop();
		if (!marcas[v.first]) {
			coste += v.second;
			cont++;
			marcas[v.first] = true;
			visitar(grafo, v, distTo, vertices, marcas);
		}
	}
	return cont == grafo.V();
}

// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int I, P, i1, i2, c;
	std::cin >> I;
	if (!std::cin)
		return false;
	std::cin >> P;
	GrafoValorado <int> grafo(I);
	for (int i = 0; i < P; ++i) {
		std::cin >> i1 >> i2 >> c;
		grafo.ponArista({ i1-1,i2-1,c });
	}
	int minimo= 0;
	
	if (resolver(grafo, minimo))
		std::cout << minimo << '\n';
	else std::cout << "No hay puentes suficientes\n";
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