// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include "PriorityQueue.h"
#include <vector>
struct comparator {
	bool operator() (int const & p1, int const & p2) {
		return p1>p2;
	}
};



// función que resuelve el problema
void resolver(int num, PriorityQueue <int, comparator> & monticuloA, PriorityQueue <int, comparator> & monticuloB) {
	bool fin = monticuloA.empty() || monticuloB.empty();
	while (!fin) {
		int i = 0;
		int horas = 0;
		std::vector<std::pair<int, char>> v;
		while (i < num && !fin) {
			int ha = monticuloA.top();
			int hb = monticuloB.top();
			monticuloA.pop();
			monticuloB.pop();
			if (ha > hb) {
				horas += hb;
				v.push_back({ ha-hb,'A' });
			}
			else {
				horas += ha;
				if (ha < hb)
					v.push_back({ hb-ha,'B' });
			}
			fin= monticuloA.empty() || monticuloB.empty();
			++i;
		}
		int j = 0;
		while (j < v.size()) {
			if (v[j].second == 'A')
				monticuloA.push(v[j].first);
			else monticuloB.push(v[j].first);
			j++;
		}
		std::cout << horas << ' ';
		fin = monticuloA.empty() || monticuloB.empty();
	}
}

// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int nDrones, nPilasA, nPilasB,carga;
	std::cin >> nDrones >> nPilasA >> nPilasB;
	if (!std::cin)
		return false;
	PriorityQueue <int, comparator> monticuloA;
	PriorityQueue <int, comparator> monticuloB;
	for (int i = 0; i < nPilasA; ++i) {
		std::cin >> carga;
		monticuloA.push({carga});
	}
	for (int i = 0; i < nPilasB; ++i) {
		std::cin >> carga;
		monticuloB.push({carga });
	}
	resolver(nDrones,monticuloA,monticuloB);

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