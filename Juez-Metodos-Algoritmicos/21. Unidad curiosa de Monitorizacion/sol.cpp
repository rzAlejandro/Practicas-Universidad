// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include<utility>
#include "PriorityQueue.h"
struct comparator {
	bool operator() (std::pair<long int, std::pair<long int, long int>> const & p1, std::pair<long int, std::pair<long int, long int>> const & p2)  {
		return p1.second.second < p2.second.second || (p1.second.second == p2.second.second && p1.first < p2.first);
	}
};

// función que resuelve el problema
void resolver(PriorityQueue<std::pair<long int, std::pair<long int, long int>>,comparator> & monticulo,int k) {
	for (int i = 0; i < k; ++i) {
		std::cout << monticulo.top().first<<'\n';
		std::pair<long int, std::pair<long int, long int>> cv = monticulo.top();
		cv.second.second += cv.second.first;
		monticulo.pop();
		monticulo.push(cv);
	}

}

// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int user,k;
	long int id, periodo;
	std::cin >> user;
	if (user==0)
		return false;
	std::pair<long int,long int> p1, p2;
	PriorityQueue <std::pair<long int, std::pair<long int, long int>>,comparator> monticulo;
	for (int i = 0; i < user; ++i) {
		std::cin >> id >> periodo;
		std::pair<long int, std::pair<long int,long int>> cv = { id,{periodo,periodo} };
		monticulo.push(cv);
	}
	std::cin >> k;
	resolver(monticulo,k);
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