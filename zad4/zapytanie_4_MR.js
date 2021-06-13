// 4. Średnie, minimalne i maksymalne BMI (waga/wzrost^2) dla osób w bazie, w podziale na narodowości
db.people.mapReduce(
  function () {
    let weight = Number(this.weight)
    let heightInMeters = Number(this.height) / 100;
    let bmi = (weight / (heightInMeters * heightInMeters))
    emit(this.nationality, { lowestBmi: bmi, highestBmi: bmi, sumBmi: bmi, count: 1 });
  },
  function (key, values) {
    return values.reduce((a, b) => {
      return {
        lowestBmi: Math.min(a["lowestBmi"], b["lowestBmi"]),
        highestBmi: Math.max(a["highestBmi"], b["highestBmi"]),
        sumBmi: a["sumBmi"] + b["sumBmi"],
        count: a["count"] + b["count"]
      }
    })
  },
  {
    finalize: function (key, value) {
      return { 
        lowestBmi: value.lowestBmi,
        highestBmi: value.highestBmi,
        averageBmi: value.sumBmi / value.count
      };
    }, 
    out: 'bmis_per_nationality'
  }
);

printjson(db.bmis_per_nationality.find().toArray());
