// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include <vector>
#include <algorithm>


// función que resuelve el problema


// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int n, n1;
	std::cin >> n;
	if (n==0)
		return false;
	std::vector<int> v1;
	std::vector<int> v2;
	for (int i = 0; i < n; ++i) {
		std::cin >> n1;
		v1.push_back(n1);
	}
	for (int i = 0; i < n; ++i) {
		std::cin >> n1;
		v2.push_back(n1);
	}
	std::sort(v1.begin(),v1.end());
	std::sort(v2.begin(), v2.end());
	bool stop = false;
	int i = 0;
	int sol = 0;
	while (i < n && !stop) {
		if (v1[i] >= v2[n - i - 1])
			stop = true;
		else sol += v2[n - i - 1] - v1[i];
		++i;
	}
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