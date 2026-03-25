<?php

// this file will be responsible for creating the tasks that the cron job will run

namespace App\Models;

class Task
{
    public function __construct(private array $taskDetails)
    {
        // create the uid here
    }

    public function execTask(): array {
        // run task

        return $this->taskDetails;
    }

    private function saveTask(): int|bool
    {
        // file_put_contents???
        return 10;
    }
}