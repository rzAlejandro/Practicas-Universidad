// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include "PriorityQueue.h"


// función que resuelve el problema
long int resolver(PriorityQueue<long int> & monticulo) {
	if (monticulo.empty())
		return 0;
	else {
		if (monticulo.size() == 1){
			return 0;
		}
		else {
			long int s1 = monticulo.top();
			monticulo.pop();
			long int s2 = monticulo.top();
			monticulo.pop();
			long int s = s1 + s2;
			monticulo.push(s);
			return s + resolver(monticulo);
		}
		
	}

}

// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int N, num;
	std::cin >> N;
	if (N==0)
		return false;
	PriorityQueue <long int> monticulo;
	for (int i = 0; i < N; ++i) {
		std::cin >> num;
		monticulo.push(num);
	}
	long int sol = resolver(monticulo);
	// escribir sol
	std::cout << sol << '\n';

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