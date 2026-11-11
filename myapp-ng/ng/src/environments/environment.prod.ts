
const getApiBaseUrl = (): string => {
	const pathname = typeof window !== 'undefined' ? window.location.pathname : '/';
	const isDeployed = pathname.startsWith('/myapp') || pathname.includes('/myapp/');
	const contextPath = isDeployed ? '/myapp' : '';
	return `${contextPath}/rest/api/products`;
};

export const environment = {
	production: true,
    USER_URL: 'https://jsonplaceholder.typicode.com/users',
    VEHICLE_URL: 'https://swapi.dev/api/vehicles',
    FILMS_URL: 'https://swapi.dev/api/films',
    BASE_URL: getApiBaseUrl()
};
