// 1. Średnią wagę i wzrost osób w bazie z podziałem na płeć (tzn. osobno mężczyzn, osobno kobiet)
printjson(
  db.people.aggregate([
    {
      $group: {
        _id: '$sex',
        totalWeight: { $sum: { "$convert": { 'input': '$weight', 'to': 'double' } } },
        totalHeight: { $sum: { "$convert": { 'input': '$height', 'to': 'double' } } },
        total: { $sum: 1 }
      }
    },
    {
      $project: {
        averageWeight: {
          $divide: ['$totalWeight', '$total']
        },
        averageHeight: {
          $divide: ['$totalHeight', '$total']
        }
      }
    }
  ]).toArray()
);