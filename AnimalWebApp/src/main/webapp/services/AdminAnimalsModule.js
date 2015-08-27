angular.module('AdminAnimalsModule', ['AdminAnimalsValues', 'LocalStorageModule'])
    .service('AdminAnimalsService', ['$http', '$q', 'localStorageService', 'AdminAnimalsValues', 'RESOURCES',
        function($http, $q, localStorageService, AdminAnimalsValues, RESOURCES) {

        $http.defaults.headers.common['AccessToken'] = localStorageService.get('accessToken');

        /**
         * filter instance used for lookup.
         * @return list of animals.
         */
        this.getAnimals = function() {
            var def = $q.defer();

            $http.post(RESOURCES.ANIMALS_FOR_ADMIN, AdminAnimalsValues.filter)
                .success(function(data) {
                    AdminAnimalsValues.animals.values = data;
                    def.resolve(data);
                })
                .error(function() {
                    def.reject("Failed to get animals");
                });

            return def.promise;
        }

        /**
         * filter instance used for lookup.
         * @return count of rows for pagination.
         */
        this.getPagesCount = function() {
            var def = $q.defer();

            $http.post(RESOURCES.ANIMALS_FOR_ADMIN_PGINATOR, AdminAnimalsValues.filter)
                .success(function(data) {
                    AdminAnimalsValues.totalItems.count = data.rowsCount;
                    def.resolve(data);
                })
                .error(function() {
                    def.reject("Failed to get page count");
                });

            return def.promise;
        }

        /**
         * @param animalId id of animal used for lookup.
         * @return animal instance.
         */
        this.getAnimal = function(animalId) {
            var def = $q.defer();

            if (AdminAnimalsValues.animal != undefined) {
                if (AdminAnimalsValues.animal.id != undefined) {
                    if (AdminAnimalsValues.animal.id == animalId) {
                        def.resolve(AdminAnimalsValues.animal);
                        return def.promise;
                    }
                }
            }

            $http.get(RESOURCES.ANIMAL_FOR_ADMIN + animalId)
                .success(function(data) {
                    angular.copy(data, AdminAnimalsValues.animal);
                    def.resolve(data);
                })
                .error(function() {
                    def.reject("Failed to get animal");
                });

            return def.promise;
        }

        /**
         * delete animal.
         */
        this.deleteAnimal = function(animalId) {
            var def = $q.defer();

            $http.delete(RESOURCES.ANIMAL_FOR_ADMIN_DELETE + animalId)
                .success(function(data) {
                    def.resolve(data);
                })
                .error(function() {
                    def.reject("Failed to delete animal");
                });

            return def.promise;
        }

        /**
         * update animal.
         */
        this.updateAnimal = function(animal) {
            var def = $q.defer();

            $http.post(RESOURCES.ANIMAL_FOR_ADMIN_UPDATE, animal)
                .success(function(data) {
                    AdminAnimalsValues.animal.image = data.filePath;
                    def.resolve(data);
                })
                .error(function() {
                    def.reject("Failed to update animal");
                });

            return def.promise;
        }

        /**
         * @return list of animal types.
         */
        this.getAnimalTypes = function() {
            var def = $q.defer();

            if (AdminAnimalsValues.animalTypes.values.length !== 0) {
                def.resolve(AdminAnimalsValues.animalTypes.values);
                return def.promise;
            }

            $http.get(RESOURCES.ANIMAL_TYPES)
                .success(function(data) {
                    AdminAnimalsValues.animalTypes.values = data;
                    def.resolve(data);
                })
                .error(function() {
                    def.reject("Failed to get animal types");
                });

            return def.promise;
        }

        /**
         * @return list of animal services.
         */
        this.getAnimalServices = function() {
            var def = $q.defer();

            if (AdminAnimalsValues.animalServices.values.length !== 0) {
                def.resolve(AdminAnimalsValues.animalServices.values);
                return def.promise;
            }

            $http.get(RESOURCES.ANIMAL_SERVICES)
                .success(function(data) {
                    AdminAnimalsValues.animalServices.values = data;
                    def.resolve(data);
                })
                .error(function() {
                    def.reject("Failed to get animal services");
                });

            return def.promise;
        }

        /**
         * @return list of animal breeds according to animal type.
         */
        this.getAnimalBreeds = function(animalTypeId) {
            var def = $q.defer();

            $http.get(RESOURCES.ANIMAL_BREEDS + animalTypeId)
                .success(function(data) {
                    def.resolve(data);
                })
                .error(function() {
                    def.reject("Failed to get breeds");
                });

            return def.promise;
        }

    }]);