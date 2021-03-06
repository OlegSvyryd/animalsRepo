angular.module('AnimalsDetailedDoctorController', ['AnimalsDoctorModule', 'AnimalsDoctorValues'])
    .controller('AnimalsDetailedDoctorController', ['$scope', '$routeParams', 'AnimalsDoctorService', 'AnimalsDoctorValues',
        function($scope, $routeParams, AnimalsDoctorService, AnimalsDoctorValues) {

            AnimalsDoctorService.rolesAllowed('лікар');

            //initialize loading spinner
            var targetContent = document.getElementById('loading-block');
            new Spinner(opts).spin(targetContent);
            //This variable decides when spinner loading for contentis closed.
            $scope.contentLoading = 1;

            var animalId = $routeParams.animalId;       //animal id
            $scope.animal = AnimalsDoctorValues.animal;  //animal
            $scope.animalImage = undefined;

            /**
             * @param animalId id of animal used for lookup.
             * @return animal instance.
             */
            AnimalsDoctorService.getAnimal(animalId)
                .finally(function() {
                    $scope.animalImage = "resources/img/noimg.png";
                    if (AnimalsDoctorValues.animal.image != undefined) {
                        if (AnimalsDoctorValues.animal.image.length > 0) {
                            $scope.animalImage = AnimalsDoctorValues.animal.image;
                        }
                    }

                    $scope.contentLoading--;
                });
        }]);