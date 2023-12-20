var map;
var clusterer;
let area = [[51.36797765022104,45.439861987792945], [51.71308855448513,46.57488701220701]];
let center = [51.533562, 46.034266];
let zoom = 13;


ymaps.ready(init);

// Функция для считывания json-файла
function readFile(file, callback) {
    var rawFile = new XMLHttpRequest();
    rawFile.overrideMimeType("application/json");
    rawFile.open("GET", file, true);
    rawFile.onreadystatechange = function() {
        if (rawFile.readyState === 4 && rawFile.status == "200") {
            callback(rawFile.responseText);
        }
    }
    rawFile.send(null);
}



var Clasters= [];
for (var i=0;i<7;i++)
    Clasters[i] = [];

function init() {
    // Создание экземпляра карты и его привязка к контейнеру с заданным id
	map = new ymaps.Map('map', {
		center: center,
		zoom: zoom
	}, {
        // Зададим Велопарковки ограниченную область прямоугольником, примерно
        // описывающим город
        restrictMapArea: area
    }),
    clusterer = new ymaps.Clusterer({
        // Макет метки кластера pieChart
        clusterIconLayout: 'default#pieChart',
        // Радиус диаграммы в пикселях
        clusterIconPieChartRadius: 20,
        // Радиус центральной части макета
        clusterIconPieChartCoreRadius: 10,
        // Ширина линий-разделителей секторов и внешней обводки диаграммы
        clusterIconPieChartStrokeWidth: 2,
        // Определяет наличие поля balloon
        hasBalloon: true
    });

         var hintOptions = {
            layout: ymaps.templateLayoutFactory.createClass('<div class="my-hint">$[properties.hintContent]</div>'),
            pane: 'balloon',
            offset: [0, -30],
            zIndex: 1000
          };


    fetch("map/placeMarks")
      .then((response) => response.json())
      .then((json) => {
        for (let i = 0; i < json.length; i++) {
            console.log(json[i].tag);

            fetch("map/placeMarks/images/" + json[i].id).then((response) =>
            response.json()).then((images)=> {
                console.log(images);


            ymaps.geocode(json[i].address, {
                        boundedBy: area,    // ограничение области поиска
                        strictBounds: true, // строгость ограничения
                        results: 1          // требуемое количество результатов
                    }).then(function (res) {
                            // Выбираем первый результат геокодирования
                            var geoObj = res.geoObjects.get(0);
                            // Координаты геообъекта
                            var coords = geoObj.geometry.getCoordinates()

                            // Определение цвета метки по соответствующему тегу проблемы
                            switch (json[i].tag) {
                                case 'BikeRental':
                                    var color = 'LightSeaGreen';
                                    break;
                                case 'BikeParking':
                                    var color = 'Blue';
                                    break;
                                case 'BikeRepairs':
                                    var color = 'Grey';
                                    break;
                                case 'BikeShop':
                                    var color = 'Yellow';
                                    break;
                                case 'DangerousArea':
                                    var color = 'Red';
                                    break;
                                case 'ChillZone':
                                    var color = 'LawnGreen';
                                    break;
                                case 'SportSection':
                                    var color = 'DeepPink';
                                    break;
                                default:
                                    var color = '#808080';
                                    break;
                            }


                            var slider = `У данного места пока нет фото`;

                            if (images.length > 0){
                                slider = ``;
                                for (let i=0;i<images.length;i++) {
                                    now = images[i].src
                                    slider=slider +
                                    `
                                    <img
                                      src=${now}
                                      width="200" height="200"
                                    />
                                    `
                                    ;
                                }
                            }

                            var title = json[i].title;
                            // Создание и добавление метки в кластер
                            place_mark = new ymaps.Placemark(coords, {
                                         balloonContent:
                                         `
                                             <div class = "balloon">
                                                <div class = "balloon_title">
                                                    ${json[i].title}
                                                </div>
                                                <div class = "balloon_info">
                                                     ${json[i].description}
                                                </div>
                                                <div class = "balloon_type">
                                                    ${json[i].tag}
                                                </div>

                                             </div>
                                         `
                                         + slider,
                                         hintContent: title
                                     }, {
                                         preset: 'islands#dotIcon',
                                         iconColor: color,
                                     }, hintOptions)
                            clusterer.add(place_mark);

                            switch (json[i].tag) {
                                case 'BikeRental':
                                    Clasters[0].push(place_mark);
                                    break;
                                case 'BikeParking':
                                    Clasters[1].push(place_mark);
                                    break;
                                case 'BikeRepairs':
                                    Clasters[2].push(place_mark);
                                    break;
                                case 'BikeShop':
                                    Clasters[3].push(place_mark);
                                    break;
                                case 'DangerousArea':
                                    Clasters[4].push(place_mark);
                                    break;
                                case 'ChillZone':
                                    Clasters[5].push(place_mark);
                                    break;
                                case 'SportSection':
                                    Clasters[6].push(place_mark);
                                    break;
                                default:
                                    var color = '#808080';
                                    break;
                            }
                            });
        });
        }
      });

    map.geoObjects.add(clusterer);         // вывод кластеров с геообъектами
    map.controls.remove('trafficControl'); // удаляем контроль трафика
}


function show_place_mark(num){
    for(var i = 0; i < Clasters[num].length; i++){
        clusterer.remove(Clasters[num][i]);
    }
}

function hide_place_mark(num){
    for(var i = 0; i < Clasters[num].length; i++){
            clusterer.add(Clasters[num][i]);
        }
}

function card_on_click(number_card) {
    checkbox = document.getElementById("checkbox_card_" + number_card);
    checkbox.checked = !checkbox.checked;
    if (!checkbox.checked)
        show_place_mark(number_card);
    else
        hide_place_mark(number_card);

    console.log(Clasters)
}

