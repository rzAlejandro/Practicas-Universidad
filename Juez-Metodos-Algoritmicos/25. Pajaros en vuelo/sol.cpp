// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include <vector>
#include "PriorityQueue.h"


struct comparator {
	bool operator() (int const & p1, int const & p2) {
		return p1>p2;
	}
};

// función que resuelve el problema


// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	long int num, num1;
	int parejas;
	std::cin >> num >> parejas;
	if (num==0 && parejas==0)
		return false;
	PriorityQueue <long int, comparator> maximosMenores;
	PriorityQueue <long int> minimosMayores;
	maximosMenores.push(num);
	minimosMayores.push(num);
	for (int i = 0; i < parejas; ++i) {
		std::cin >>num >> num1;
		if (num < maximosMenores.top()) {
			maximosMenores.push(num);

			if (num1 < maximosMenores.top()) {//Meter los dos en 1 monticulo
				maximosMenores.push(num1);
				if (maximosMenores.size() > minimosMayores.size() + 2) {
					long int n = maximosMenores.top();
					maximosMenores.pop();
					minimosMayores.push(n);
				}
				if (maximosMenores.size() == minimosMayores.size() + 2)
					maximosMenores.pop();
				std::cout<< maximosMenores.top()<<' ';
			}
			else { //Uno en cada monticulo.
				minimosMayores.push(num1);
				if (maximosMenores.size()>minimosMayores.size())
					std::cout << maximosMenores.top() << ' ';
				else 
					std::cout << minimosMayores.top() << ' ';
			}
		}
		else {
			minimosMayores.push(num);

			if (num1 < maximosMenores.top()) {//Uno en cada monticulo.
				maximosMenores.push(num1);

				if (maximosMenores.size() > minimosMayores.size())
					std::cout << maximosMenores.top() << ' ';
				else
					std::cout << minimosMayores.top() << ' ';
			}
			else {//Meter los dos en 1 monticulo

				minimosMayores.push(num1);

				if (minimosMayores.size() > maximosMenores.size() + 2) {
					long int n = minimosMayores.top();
					minimosMayores.pop();
					maximosMenores.push(n);
				}
				if (minimosMayores.size() == maximosMenores.size() + 2)
					minimosMayores.pop();
				std::cout << minimosMayores.top() << ' ';
			}
		}
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