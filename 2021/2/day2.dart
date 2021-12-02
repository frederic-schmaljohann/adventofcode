import 'dart:io';

import 'package:collection/collection.dart';

void part1(List<String> lines) {
  var horizontal = <int>[];
  var depth = <int>[];
  lines.forEach((element) {
    if (element.startsWith("down ")) {
      depth.add(int.parse(element.split(" ")[1]));
    } else if (element.startsWith("up ")) {
      depth.add((int.parse(element.split(" ")[1])) * -1);
    } else if (element.startsWith("forward ")) {
      horizontal.add((int.parse(element.split(" ")[1])));
    }
  });

  print(horizontal.sum * depth.sum);
}

void part2(List<String> lines) {
  var horizontal = <int>[];
  var depth = <int>[];
  var aim = <int>[];

  lines.forEach((element) {
    if (element.startsWith("down ")) {
      aim.add(int.parse(element.split(" ")[1]));
    } else if (element.startsWith("up ")) {
      aim.add((int.parse(element.split(" ")[1])) * -1);
    } else if (element.startsWith("forward ")) {
      horizontal.add((int.parse(element.split(" ")[1])));
      depth.add((int.parse(element.split(" ")[1]) * aim.sum));
    }
  });

  print(horizontal.sum * depth.sum);
}

main() {
  new File(
          "input.txt")
      .readAsLines()
      .then((value) {
    part1(value);
    part2(value);
  });
}
