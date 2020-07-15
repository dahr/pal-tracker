package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private final Map<Long,TimeEntry> timeEntryMap = new HashMap<>();
    private AtomicLong counter = new AtomicLong(1L);

    @Override
    public TimeEntry create(TimeEntry timeEntryToCreate) {
        long id = counter.getAndIncrement();
        TimeEntry timeEntryCreated = new TimeEntry(id,
                timeEntryToCreate.getProjectId(), timeEntryToCreate.getUserId(),
                timeEntryToCreate.getDate(), timeEntryToCreate.getHours());
        timeEntryMap.put(timeEntryCreated.getId(), timeEntryCreated);
        return timeEntryCreated;
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntryMap.values());
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntryMap.get(id);
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntryToUpdate) {
        if (timeEntryMap.containsKey(id)) {
            TimeEntry timeEntryCreated = new TimeEntry(id,
                    timeEntryToUpdate.getProjectId(), timeEntryToUpdate.getUserId(),
                    timeEntryToUpdate.getDate(), timeEntryToUpdate.getHours());
            timeEntryMap.put(timeEntryCreated.getId(), timeEntryCreated);
            return timeEntryCreated;
        } else return null;
    }

    @Override
    public void delete(long id) {
        timeEntryMap.remove(id);
    }
}
