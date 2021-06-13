// 1. Średnią wagę i wzrost osób w bazie z podziałem na płeć (tzn. osobno mężczyzn, osobno kobiet)
db.people.mapReduce(
  function () {
    emit(this.sex, { weight: Number(this.weight), height: Number(this.height), count: 1 });
  },

  function (key, values) {
    return { 
            height : Array.sum(values.map(e => e["height"])), 
            weight : Array.sum(values.map(e => e["weight"])), 
            count: Array.sum(values.map(e => e["count"])) 
    };
  },
  {
        finalize: function(key, value) {
            return { "averageHeight": (value.height / value.count), "averageWeight": (value.weight / value.count) }
        },
        out: 'average_weight_and_height_per_sex'
  }
)

printjson(db.average_weight_and_height_per_sex.find().toArray());