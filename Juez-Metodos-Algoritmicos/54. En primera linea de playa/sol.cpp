// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include <vector>
#include <algorithm>
bool myfunction(std::pair<int, int> i, std::pair<int, int> j) {
	return i.first < j.first;
}


bool perteneceInt(int x, std::pair<int, int> interv) {
	return x >= interv.first && x < interv.second;
}
// función que resuelve el problema


// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int N;
	long int wi, ei;
	std::cin >> N;
	if (N==0)
		return false;
	std::vector<std::pair<int, int>> v1;
	std::vector<std::pair<int, int>> intervalos;
	for (int i = 0; i < N; ++i) {
		std::cin >> wi >> ei;
		v1.push_back({ wi,ei });
	}
	std::sort(v1.begin(), v1.end(), myfunction);
	int sol = 1;
	intervalos.push_back({ v1[0].first,v1[0].second });
	for (int i = 1; i < v1.size(); ++i) {
		bool stop = false;
		int j = 0;
		if (perteneceInt(v1[i].first, intervalos[intervalos.size() - 1])) {
			intervalos[intervalos.size() - 1].first = v1[i].first;
			if (v1[i].second <= intervalos[intervalos.size() - 1].second) {//esta dentro de un intervalo
				intervalos[intervalos.size() - 1].second = v1[i].second;
			}
		}
		else {
			sol++;
			intervalos.push_back(v1[i]);
		}
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