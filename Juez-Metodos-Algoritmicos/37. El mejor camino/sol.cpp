// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include"GrafoValorado.h"
#include "PriorityQueue.h"
#include<vector>
#include<climits>
struct comparator {
	bool operator() (std::pair<int,int> const & p1, std::pair<int,int> const & p2) {
		return p1.second< p2.second;
	}
};

void relax(std::pair<int, int> v,Arista<int> a,std::vector<int>& distMin,bool valor, PriorityQueue<std::pair<int, int>, comparator> & vert1) {
	int w = a.otro(v.first);
	if (!valor) {
		if (distMin[w] > distMin[v.first] + 1) {
			distMin[w] = distMin[v.first] + 1;
			vert1.push({ w,distMin[w] });
		}
	}
	else {
		if (distMin[w] > distMin[v.first] + a.valor()) {
			distMin[w] = distMin[v.first] + a.valor();
			vert1.push({ w,distMin[w] });
		}
	}
	
}

bool dijkstra(GrafoValorado <int> grafo,std::vector<int>& distMin,int origen, int destino,bool valor,int & sol,int & cont) {
	PriorityQueue<std::pair<int, int>, comparator> vert1;
	std::vector<bool> marcas(grafo.V(), false);
	vert1.push({ origen,0 });
	marcas[0] = true;
	distMin[origen] = 0;
	bool enc = false;
	while (!vert1.empty() && !enc) {
		std::pair<int, int> v = vert1.top();
		vert1.pop();
		if (!marcas[v.first]){
			marcas[v.first] = true;
			sol += v.second;
			cont++;
			if (v.first == destino)
				enc = true;
			else {
				std::vector<Arista<int>>adya = grafo.ady(v.first);
				for (int i = 0; i < adya.size(); ++i) {
					relax(v, adya[i], distMin, valor,vert1);
				}
			}
		}
	}
	return enc;
}
// función que resuelve el problema
void resolver(GrafoValorado <int> grafo,int origen,int destino) {
	int n = grafo.V();
	std::vector<int> distMin1(n, INT_MAX);
	std::vector<int> distMin2(n, INT_MAX);
	bool valor=true;
	int cont = 0;
	int sol = 0;
	int b = 0;
	int c = 0;
	if (dijkstra(grafo,distMin1,origen, destino,valor,sol,cont)) {
		valor = false;
		std::cout << sol << ' ';
		if (dijkstra(grafo,distMin2, origen, destino, valor,b, c)) {
			if (distMin2[destino] +1== cont)
				std::cout << "SI\n";
			else std::cout << "NO\n";
		}
	}
	else
		std::cout << "SIN CAMINO\n";
}

// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int n, a, i1, i2, valor;
	std::cin >> n >> a;
	if (!std::cin)
		return false;
	GrafoValorado <int> grafo(n);
	for (int i = 0; i < a; ++i) {
		std::cin >> i1 >> i2 >> valor;
		grafo.ponArista({ i1 - 1,i2 - 1,valor });
	}
	int k, v1, v2;
	std::cin >> k;
	for (int i = 0; i < k; ++i) {
		std::cin >> v1 >> v2;
		resolver(grafo, v1-1, v2-1);
	}

	// escribir sol

	std::cout << "---\n";
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