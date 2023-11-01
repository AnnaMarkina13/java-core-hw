package ru.markina.hw3.worker.util;

import ru.markina.hw3.worker.Worker;

import java.util.Iterator;
import java.util.List;

public record WorkTeam(List<Worker> workers) implements Iterable<Worker> {

    @Override
    public Iterator<Worker> iterator() {
        return new WorkTeamIterator(workers);
    }

    protected static class WorkTeamIterator implements Iterator<Worker> {
        private final Iterator<Worker> workers;

        public WorkTeamIterator(final List<Worker> workers) {
            this.workers = workers.iterator();
        }

        @Override
        public boolean hasNext() {
            return workers.hasNext();
        }

        @Override
        public Worker next() {
            return workers.next();
        }

        @Override
        public void remove() {
            workers.remove();
        }
    }
}
