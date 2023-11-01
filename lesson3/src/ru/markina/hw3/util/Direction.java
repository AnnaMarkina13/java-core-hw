package ru.markina.hw3.util;

import java.util.Comparator;

/**
 * Класс для выбора порядка сортировки компаратором
 */
public enum Direction {
    ASC {
        @Override
        public <T> Comparator<T> applyDirection(final Comparator<T> comparator) {
            return comparator;
        }
    },
    DESC {
        @Override
        public <T> Comparator<T> applyDirection(final Comparator<T> comparator) {
            return comparator.reversed();
        }
    };

    public abstract <T> Comparator<T> applyDirection(Comparator<T> comparator);
}