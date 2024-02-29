package app;

public class Operation {
    public String additionalSum(String firstAdditional, String secondAdditional) {
        // Дополним строки нулями слева, если их длины отличаются
        int maxLength = Math.max(firstAdditional.length(), secondAdditional.length());
        if ((firstAdditional.length() < maxLength) && firstAdditional.charAt(0) == '0') {
            firstAdditional = String.format("%" + maxLength + "s", firstAdditional).replace(' ', '0');
        } else if ((firstAdditional.length() < maxLength) && firstAdditional.charAt(0) == '1') {
            firstAdditional = String.format("%" + maxLength + "s", firstAdditional).replace(' ', '1');
        }
        if ((secondAdditional.length() < maxLength) && secondAdditional.charAt(0) == '0') {
            secondAdditional = String.format("%" + maxLength + "s", secondAdditional).replace(' ', '0');
        } else if ((secondAdditional.length() < maxLength) && secondAdditional.charAt(0) == '1') {
            secondAdditional = String.format("%" + maxLength + "s", secondAdditional).replace(' ', '1');
        }
        StringBuilder result = getAdditionalSumResult(firstAdditional, secondAdditional, maxLength);

        return result.toString(); // Возвращаем результат в виде строки
    }

    private StringBuilder getAdditionalSumResult(String first, String second, int maxLength) {
        StringBuilder result = new StringBuilder(); // Используем StringBuilder для эффективной работы со строками

        int carry = 0;

        // Итерация по разрядам в обратном порядке
        for (int i = maxLength - 1; i >= 0; i--) {
            int sum = (first.charAt(i) - '0') + (second.charAt(i) - '0') + carry; // Сумма текущих разрядов и переноса
            result.insert(0, sum % 2); // Добавление результата в начало строки
            carry = sum / 2; // Вычисление нового значения переноса
        }

        return result;
    }

    public String getFinalAdditionalSumResult (String firstAdditional, String secondAdditional, String sumResult) {
        StringBuilder stringBuilder = new StringBuilder(sumResult);
        if(firstAdditional.charAt(0) == '1' && secondAdditional.charAt(0) == '1') {
            stringBuilder.insert(0, '1');
        }
        Converter converter = new Converter();
        if (stringBuilder.charAt(0) == '1') {
            return converter.directToAdditional(stringBuilder.toString());
        }
        return sumResult;
    }
}
