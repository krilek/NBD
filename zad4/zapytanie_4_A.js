// 4. Średnie, minimalne i maksymalne BMI (waga/wzrost^2) dla osób w bazie, w podziale na narodowości
printjson(
  db.people.aggregate([
    {
      $addFields: {
        bmi: {
          $divide: [
            { "$convert": { 'input': '$weight', 'to': 'double' } },
            {
              $multiply: [
                { $divide: [{ "$convert": { 'input': '$height', 'to': 'double' } }, 100] },
                { $divide: [{ "$convert": { 'input': '$height', 'to': 'double' } }, 100] }
              ]
            }
          ]
        }
      }
    },
    {
      $group: {
        _id: '$nationality',
        avarageBmi: { $avg: '$bmi' },
        highestBmi: { $max: '$bmi' },
        lowestBmi: { $min: '$bmi' }
      }
    }
  ]).toArray()
);